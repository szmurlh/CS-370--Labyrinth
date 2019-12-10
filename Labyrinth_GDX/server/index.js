var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var players = [];
var playerCount = 0;

server.listen(8080, function() {
    console.log("Server is now running...")
});

io.on('connection', function(socket){
    socket.emit('addPlayer', { playerCount: playerCount });
    console.log(playerCount)
    socket.on('joinSession', function(data) {

            var newPlayer = new player(data.name, data.turn, socket.id);
            players.push(newPlayer);
            console.log(players);

            io.emit('currentPlayers', players);
    });
    socket.on('updateBoardState', function(data) {
        io.emit('updateBoardState', data)
    });
    socket.on('updatePlayerTurn', function(data) {
        for(var i = 0; i < players.length; i++) {
            players[i].turn = data[i].turn
        }

        io.emit('updateTurns', players);
    }); 
    console.log("Player Connected");
    socket.on('disconnect', function(){

        for(var i = 0; i < players.length; i++){
            if(players[i].id === socket.id){
                players.splice(i, 1)
            }
        }
        playerCount--;

        io.emit('updatePlayerCount', { playerCount: playerCount});
        io.emit('playerDisconnected', { id: socket.id})
        console.log("Player Disconnected")
        console.log(playerCount)
    });
    
    playerCount++;
});

function player(name, turn, id) {
    this.id = id;
    this.name = name;
    this.turn = turn;
}
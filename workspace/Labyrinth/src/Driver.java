import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWVidMode;

public class Driver {
	
	private static int WINDOW_WIDTH = 640;
	private static int WINDOW_HEIGHT = 480;
	
	public static void main(String[] args) {
		
		if (!glfwInit()) {
			throw new IllegalStateException("Failed to initialize GLFW! ");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		long window = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "CS 370 - Labyrinth", 0, 0);
		if (window == 0) {
			throw new IllegalStateException("Failed to create window! ");
		}
		
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (videoMode.width() - WINDOW_WIDTH)/2, (videoMode.height() - WINDOW_HEIGHT)/2);
		
		glfwShowWindow(window);
		
		while (!glfwWindowShouldClose(window)) {
			glfwPollEvents();
		}
		
		glfwTerminate();
		
	}
	
}

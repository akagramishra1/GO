import classes from "../"classes.c" //imported class file written in C lang

public class Main {
        public static float halfWidth = 0;
        public static float zNear = 0;
        public static float zFar = 0;
    
       //  Hello team I have created a hava constructor here ~Akagra
        public Game(float halfWidth,float zNear,float zFar)
        {
            this.halfWidth = halfWidth;
            this.zNear = zNear;
            this.zFar = zFar;
            
            init();
        }
    
        private void init() {
            try {
                DisplayMode mode = Display.getDesktopDisplayMode();
                Display.setDisplayMode(mode);
                Display.create();
                GL11.glViewport(0,0,mode.getWidth(),mode.getHeight());
                // setup frustum
                GL11.glMatrixMode(GL11.GL_PROJECTION);
                GL11.glLoadIdentity();
    
                float ratio = halfWidth * mode.getHeight()/mode.getWidth();
                GL11.glFrustum(-halfWidth, halfWidth, -ratio, ratio, zNear, zFar);
    
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
    
            } catch (LWJGLException ex) {
                System.out.println("Cannot create the display");
            }
        }
    
        protected void play()
        {
            while(!Display.isCloseRequested())
            {
    
    
                Display.update();
            }

            classes.fire.src="classes/classes.c";
            
        Display.destroy();
        }
    }
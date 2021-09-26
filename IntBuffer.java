package game.src.main.java;

public static IntBuffer loadTexture(BufferedImage image) {

    // Put Image In Memory
    if (image != null) {

        int textureWidth = image.getWidth();
        int textureHeight = image.getHeight();
// read the number of colour components reqquired for the image
        int numComponents = image.getColorModel().getNumComponents();
// define an image type for the following BufferedImage appropriate for the number of colour components.
        int type = image.getType();

        if (numComponents == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        } else {
            type = BufferedImage.TYPE_4BYTE_ABGR;
        }


// create a BufferedImage of the appropriate type
        BufferedImage bi = new BufferedImage(textureWidth, textureHeight, type);
        Graphics2D g = bi.createGraphics();
        g.scale(1, -1);
        g.drawImage(image, 0, -textureHeight, null);
// create an array with a length that is dependent on the number of colour componets.
        byte[] data = null;
        data = (byte[]) bi.getRaster().getDataElements(0, 0, textureWidth, textureHeight, null);


        ByteBuffer texels = BufferUtils.createByteBuffer(data.length);
        texels.clear();
        texels.put(data);
        texels.flip();

        // Create A IntBuffer For Image Address In Memory
        IntBuffer texture = BufferUtils.createIntBuffer(1);
        GL11.glGenTextures(texture); // Create Texture In OpenGL

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.get(0));

        // Typical Texture Generation Using Data From The Image

        // Linear Filtering
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        // Linear Filtering
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        // Generate The Texture

        switch (numComponents) {

            case (4): {
                GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, image.getWidth(),
                     image.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, texels);
                break;
            }
            case (1):
            case (3):
            {
                GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, image.getWidth(),
                     image.getHeight(), 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, texels);
                break;
            }
        }

        GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);

        return texture; // Return Image Address In Memory ~ Akagra
    } else {
        return null;
    }
}
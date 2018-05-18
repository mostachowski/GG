package NeuralNetwork;

import java.awt.image.BufferedImage;

public class ImageProcessing
{
    //Convert RGB To Matrix [Of Double]
    public static double[] ToMatrix(BufferedImage BM, int MatrixRowNumber, int MatrixColumnNumber)
    {
        double HRate = ((double)MatrixRowNumber / BM.getHeight());
        double WRate = ((double)MatrixColumnNumber / BM.getWidth());
        double[] Result = new double[MatrixColumnNumber * MatrixRowNumber];

        for (int r = 0; r < MatrixRowNumber; r++)
        {
            for (int c = 0; c < MatrixColumnNumber; c++)
            {

                int clr=  BM.getRGB((int)(c/WRate),(int)(r/HRate));
                int  red   = (clr & 0x00ff0000) >> 16;
                int  green = (clr & 0x0000ff00) >> 8;
                int  blue  =  clr & 0x000000ff;

                Result[r * MatrixColumnNumber + c] = 1 - (red * .3 + green * .59 + blue * .11) / 255;
            }
        }
        return Result;
    }

    //Convert Double To Grey Level
  /*  public static BufferedImage ToImage(double[] Matrix, int MatrixRowNumber, int MatrixColumnNumber,
                                 int ImageHeight, int ImageWidth)
    {
        double HRate = ((double)ImageHeight / MatrixRowNumber);
        double WRate = ((double)ImageWidth / MatrixColumnNumber);
        BufferedImage Result = new BufferedImage(ImageWidth, ImageHeight);

        for (int i = 0; i < ImageHeight; i++)
        {
            for (int j = 0; j < ImageWidth; j++)
            {
                int x = (int)((double)j / WRate);
                int y = (int)((double)i / HRate);

                double temp = Matrix[y * MatrixColumnNumber + x];
                Result.SetPixel(j, i, Color.FromArgb((int)((1 - temp) * 255), (int)((1 - temp) * 255), (int)((1 - temp) * 255)));

            }
        }
        return Result;
    }
    */

    //public static Bitmap Scale(Bitmap Input, int newHeight, int newWidth)
    //{
    //    double HRate = (double)Input.Height / newHeight;
    //    double WRate = (double)Input.Width / newWidth;
    //    Bitmap Result = new Bitmap(newWidth, newHeight);
    //    for (int i = 0; i < newHeight; i++)
    //    {
    //        for (int j = 0; j < newWidth; j++)
    //        {
    //            int x = (int)((double)j * WRate);
    //            int y = (int)((double)i * HRate);
    //            Result.SetPixel(j, i, Input.GetPixel(x, y));
    //        }
    //    }

    //    return Result;
    //}
}

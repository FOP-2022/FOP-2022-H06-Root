//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package h06;


public class StrangeThings {
    public StrangeThings() {

        super();
    }

    public int computeStrangeValue1(int var1) {


        int var10000;
        if (var1 <= 0) {
            var10000 = 1;

        } else {
            var10000 = var1 % 2;

            if (var10000 == 0) {
                int var2 = var1 - 1;
                var10000 = 1 + this.computeStrangeValue1(var2);

            } else {
                int var3 = var1 - 3;
                var10000 = 1 + this.computeStrangeValue2(var3);
            }
        }

        var1 = var10000;
        return var1;
    }

    public int computeStrangeValue2(int var1) {


        if (var1 <= 0) {
            var1 = 1;

        } else {
            int var10000 = var1 % 2;

            if (var10000 == 0) {
                int var4 = var1 - 1;
                var1 = 1 + this.computeStrangeValue2(var4);

            } else {
                int var5 = var1 - 3;
                var1 = 1 + this.computeStrangeValue2(var5);
            }
        }

        return var1;
    }

    public int whatTheHellIsThat1(int var1, int var2) {
        int var10000;
        label15: {


            if (var1 > 0) {

                if (var2 > 0) {

                    if (var1 < var2) {
                        var10000 = 1 + var1;
                        int var6 = var2 - var1;
                        int var7 = var10000;
                        var10000 = this.whatTheHellIsThat1(var7, var6);

                    } else {
                        var10000 = 1 + var2;
                        int var8 = var1 - var2;
                        int var9 = var10000;
                        var10000 = this.whatTheHellIsThat1(var9, var8);
                    }
                    break label15;
                }
            }

            byte var3;
            var10000 = var3 = 0;

        }

        int var10 = var10000;
        return var10;
    }

    public int whatTheHellIsThat2(int var1, int var2) {


        int var3;
        if (var1 > 0) {

            if (var2 > 0) {

                int var10000;
                if (var1 < var2) {
                    var10000 = 1 + var1;
                    int var10 = var2 - var1;
                    int var11 = var10000;
                    var3 = this.whatTheHellIsThat2(var11, var10);

                } else {
                    var10000 = 1 + var2;
                    int var12 = var1 - var2;
                    int var13 = var10000;
                    var3 = this.whatTheHellIsThat2(var13, var12);
                }

                return var3;
            }
        }

        var3 = 0;

        return var3;
    }

    public double[] transformArrayIteratively(double[] var1, Traverser var2) {

        int var3 = var2.getFirstIndex(var1);
        int var4 = var2.getNextIndex(var3);
        double[] var5 = new double[var1.length];

        int var10001;
        int var6;
        if (var3 == 0) {
            var5[var3] = var1[var3] * 2.0D + 3.14D;
            var6 = 0;

            while(true) {
                var10001 = var1.length - 1;

                if (var6 >= var10001) {

                    break;
                }

                var5[var4] = var1[var4] * 2.0D + 3.14D;
                var4 = var2.getNextIndex(var4);
                ++var6;

            }
        } else {
            var5[var1.length - 1 - var3] = var1[var3] * 2.0D + 3.14D;
            var6 = 0;

            while(true) {
                var10001 = var1.length - 1;

                if (var6 >= var10001) {
                    break;
                }

                var5[var1.length - 1 - var4] = var1[var4] * 2.0D + 3.14D;
                var4 = var2.getNextIndex(var4);
                ++var6;

            }
        }

        return var5;
    }

    public double[] transformArrayRecursively(double[] var1, Traverser var2) {

        double[] var3 = new double[var1.length];
        byte var14 = 0;
        this.doTheRecursion(var1, var3, var2, var14);
        return var3;
    }

    public void doTheRecursion(double[] var1, double[] var2, Traverser var3, int var4) {

    }
}

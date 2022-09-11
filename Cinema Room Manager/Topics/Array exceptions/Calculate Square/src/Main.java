class FixingExceptions {

    public static void calculateSquare(int[] array, int index) {
        if (array != null && index > -1 && index < array.length) {
            System.out.printf("%.0f", Math.pow(array[index], 2));
        } else {
            System.out.println("Exception!");
        }
    }
}
package converter.abyte.mops1k.biteconverter;

public class Convert {
    private int toLevel;
    private int fromLevel;

    /**
     * toLevel setter
     * @param toLevel
     * @return int
     */
    public Convert setToLevel(int toLevel) {
        this.toLevel = toLevel;
        return this;
    }

    /**
     * fromLevel setter
     * @param fromLevel
     * @return int
     */
    public Convert setFromLevel(int fromLevel) {
        this.fromLevel = fromLevel;

        return this;
    }

    /**
     * @param level
     * @return int
     */
    private int getConvert(int level) {
        int returnDelimiter = 1;
        switch (level) {
            case 1:
                returnDelimiter = 8;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                returnDelimiter = 1024;
                break;
            default:
                returnDelimiter = 1;
                break;
        }

        return returnDelimiter;
    }

    /**
     * Calculating method
     * @param number
     * @return long
     */
    public long calculate(long number) {
        if (this.fromLevel > this.toLevel) {
            for (int i = this.fromLevel; i > this.toLevel; i--) {
                number = number * this.getConvert(i - 1);
            }
        } else {
            for (int i = this.fromLevel; i < this.toLevel; i++) {
                number = number / this.getConvert(i);
            }
        }

        return number;
    }
}

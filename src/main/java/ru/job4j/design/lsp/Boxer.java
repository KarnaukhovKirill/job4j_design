package ru.job4j.design.lsp;

public class Boxer {
    int height;
    int handsLength;

    public boolean trueBoxer() {
        return (handsLength / height) > 1;
    }

    static class Rocky extends Boxer {
        int height;
        int handsLength;
        int heart;

        /**
         * В переопределённом методе изменилось постусловие. В @return появилась ещё одна переменная.
         * @return возможность боксировать
         */
        @Override
        public boolean trueBoxer() {
            return (this.handsLength / height + heart / 100) > 1;
        }
    }
}

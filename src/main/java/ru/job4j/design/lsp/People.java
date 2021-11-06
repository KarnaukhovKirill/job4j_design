package ru.job4j.design.lsp;

public class People {
    protected int voiceQuality;

    public People(int voiceQuality) {
        this.voiceQuality = voiceQuality;
    }

    public void sing() {
        if (voiceQuality > 70) {
            System.out.println("Поёт");
        }
    }

    static class MutePeople extends People {
        public MutePeople(int voiceQuality) {
            super(voiceQuality);
        }

        /**
         * Данный класс расширяет класс People, но предусловие было усилено.
         * Хорошим решением было бы вынесение sing() в отдельный интерфейс Singer
         */
        @Override
        public void sing() {
            if (voiceQuality > 90) {
                super.sing();
            }
        }
    }
}

package com.example.builder;

public class Computer {
    private final String cpu;
    private final int ramGb;
    private final int storageGb;
    private final String gpu;

    private Computer(Builder b) {
        this.cpu = b.cpu;
        this.ramGb = b.ramGb;
        this.storageGb = b.storageGb;
        this.gpu = b.gpu;
    }

    public static class Builder {
        private String cpu;
        private int ramGb;
        private int storageGb;
        private String gpu;

        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder ramGb(int ramGb) {
            this.ramGb = ramGb;
            return this;
        }

        public Builder storageGb(int storageGb) {
            this.storageGb = storageGb;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ramGb=" + ramGb +
                ", storageGb=" + storageGb +
                ", gpu='" + gpu + '\'' +
                '}';
    }
}

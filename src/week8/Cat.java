/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week8;

public class Cat {
    private static final int DEFAULT_NUM_PAWS = 4;
    private int numPaws;
    private final String breed;

    public Cat(String breed) {
        this.numPaws = DEFAULT_NUM_PAWS;
        this.breed = breed;
    }

    public void setNumPaws(int numPaws) {
        this.numPaws = numPaws;
    }

    @Override
    public String toString() {
        return "Breed: " + breed + " Number of Paws: " + numPaws;
    }

    @Override
    public boolean equals(Object o) {
        // same memory?
        if(this == o) {
            return true;
        }
        // same type? Is o null?
        if(o == null) {
            return false;
        }

        if(!(o instanceof Cat)) {
            return false;
        }

        Cat c = (Cat) o;
        return this.numPaws == c.numPaws
                && this.breed.equals(c.breed);
    }
}

package com.techelevator.model.IGDBModels;

import java.util.Objects;

public class IGDBGameCount {

    private int count;

    public IGDBGameCount(int count) {
        this.count = count;
    }

    public IGDBGameCount() {

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "IGDBGameCount{" +
                "count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IGDBGameCount that = (IGDBGameCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}

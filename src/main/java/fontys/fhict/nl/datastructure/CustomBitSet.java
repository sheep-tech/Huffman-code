package fontys.fhict.nl.datastructure;

import java.util.BitSet;

// custom bitset class to extend BitSet Class functionality.
// This class stores which bits are actually data, including '0'
// Every set bit of dataPosition will tell where is meaningful data
public class CustomBitSet {
    private BitSet data;
    private BitSet dataPosition;

    public CustomBitSet() {
        data = new BitSet();
        dataPosition = new BitSet();
    }

    public CustomBitSet(CustomBitSet that) {
        this.data = that.getData();
        this.dataPosition = that.getDataPosition();
    }

    public void set(int index, boolean state) {
        data.set(index, state);

        dataPosition.clear();

        dataPosition.set(0, (index+1));
    }

    // remove the last bit of data bitset
    public void unsetLastBit(int index) {
        data.set(index, false);

        dataPosition.clear();
        if(index > 0)
            dataPosition.set(0, index-1);
    }

    public BitSet getData() {
        return data;
    }

    public void setData(BitSet data) {
        this.data = data.get(0, data.length());
    }

    public BitSet getDataPosition() {
        return dataPosition;
    }

    public void setDataPosition(BitSet dataPosition) {
        this.dataPosition = dataPosition.get(0, dataPosition.length());
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}

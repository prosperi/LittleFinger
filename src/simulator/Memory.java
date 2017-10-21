package simulator;

import exceptions.OutOfMemoryException;

import java.util.ArrayList;
import java.util.HashMap;

public class Memory {

    private ArrayList<Byte> _memory;
    private int _size;

    public Memory (int size) {
        _memory = new ArrayList<Byte>();
        _size = size;
    }

    public Byte fetch (int address) {
        if (address < 0 ||address > _size) throw new OutOfMemoryException("Tried to retrieve byte outside of Memory");
        return _memory.get(address);
    }

    public Byte store (int address, byte value) {
        if (address < 0 ||address > _size) throw new OutOfMemoryException("Tried to store byte outside of Memory");
        _memory.set(address, value);

        return value;
    }

    public void reset () {
        _size = 0;
        _memory.clear();
    }

    public int size () {
        return this._size;
    }
}

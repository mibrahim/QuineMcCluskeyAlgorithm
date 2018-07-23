/**
 * Copyright (c) 2013 the authors listed at the following URL, and/or
 * the authors of referenced articles or incorporated external code:
 * http://en.literateprograms.org/Quine-McCluskey_algorithm_(Java)?action=history&offset=20110925122251
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * <p>
 * Retrieved from: http://en.literateprograms.org/Quine-McCluskey_algorithm_(Java)?oldid=17357
 */

package quinemccluskeyalgorithm;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

public class Term {
    public static final byte DontCare = 2;
    private byte[] varVals;

    public Term(byte[] varVals) {
        this.varVals = varVals;
    }

    public static Term read(Reader reader) throws IOException {
        int c = '\0';
        ArrayList<Byte> temporaryArray = new ArrayList<Byte>();
        while (c != '\n' && c != -1) {
            c = reader.read();
            if (c == '0') {
                temporaryArray.add((byte) 0);
            } else if (c == '1') {
                temporaryArray.add((byte) 1);
            }
        }
        if (temporaryArray.size() > 0) {
            byte[] resultBytes = new byte[temporaryArray.size()];
            for (int i = 0; i < temporaryArray.size(); i++) {
                resultBytes[i] = (byte) temporaryArray.get(i);
            }
            return new Term(resultBytes);
        } else {
            return null;
        }

    }

    public static Term readintArray(int[] state) {
        if (state.length > 0) {
            byte[] resultBytes = new byte[state.length];
            for (int i = 0; i < state.length; i++) {
                resultBytes[i] = (byte) state[i];
            }
            return new Term(resultBytes);
        } else {
            return null;
        }

    }

    public int getNumVars() {
        return varVals.length;
    }

    public int getVarsValue(int i) {
        int value = -2;
        if (varVals[i] == DontCare) {
            value = -1;
        } else if (varVals[i] == (byte) 0) {
            value = 0;
        } else if (varVals[i] == (byte) 1) {
            value = 1;
        } else {
        }

        return value;
    }

    public String toString() {
        String result = "{";
        for (int i = 0; i < varVals.length; i++) {
            if (varVals[i] == DontCare)
                result += "X";
            else
                result += varVals[i];
            result += " ";
        }
        result += "}";
        return result;
    }

    public Term combine(Term term) {
        int diffVarNum = -1; // The position where they differ
        for (int i = 0; i < varVals.length; i++) {
            if (this.varVals[i] != term.varVals[i]) {
                if (diffVarNum == -1) {
                    diffVarNum = i;
                } else {
                    // They're different in at least two places
                    return null;
                }
            }
        }
        if (diffVarNum == -1) {
            // They're identical
            return null;
        }
        byte[] resultVars = varVals.clone();
        resultVars[diffVarNum] = DontCare;
        return new Term(resultVars);
    }

    public int countValues(byte value) {
        int result = 0;
        for (int i = 0; i < varVals.length; i++) {
            if (varVals[i] == value) {
                result++;
            }
        }
        return result;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o == null || !getClass().equals(o.getClass())) {
            return false;
        } else {
            Term rhs = (Term) o;
            return Arrays.equals(this.varVals, rhs.varVals);
        }
    }

    public int hashCode() {
        return varVals.hashCode();
    }

    boolean implies(Term term) {
        for (int i = 0; i < varVals.length; i++) {
            if (this.varVals[i] != DontCare &&
                    this.varVals[i] != term.varVals[i]) {
                return false;
            }
        }
        return true;
    }
}


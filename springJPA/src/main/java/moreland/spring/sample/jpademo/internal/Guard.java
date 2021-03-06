//
// Copyright © 2021 Terry Moreland
// Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
// to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
// and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
// WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
package moreland.spring.sample.jpademo.internal;

public class Guard {
    
    public static void guardAgainstArgumentNull(Object argument, String name) {
        if (argument == null) {
            throw new IllegalArgumentException("%s cannot be null".formatted(name));
        }
    }
    public static void guardAgainstArgumentNullOrEmpty(String argument, String name) {
        guardAgainstArgumentNull(argument, name);
        if (argument.isEmpty()) {
            throw new IllegalArgumentException("%s cannot be empty".formatted(name));
        }
    }

    public static void guardAgainstArgumentNegative(Long value, String name) {
        guardAgainstArgumentNull(value, name);
        if (value.longValue() < 0)
            throw new IllegalArgumentException("%s must have a postive value".formatted(name));

    }
    public static void guardAgainstArgumentNegative(Integer value, String name) {
        guardAgainstArgumentNull(value, name);
        if (value.intValue() < 0)
            throw new IllegalArgumentException("%s must have a postive value".formatted(name));
    }
}

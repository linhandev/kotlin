// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 118 -> sentence 118
 * NUMBER: 4
 * DESCRIPTION: ABSTRACT token in abstract class with concrete implementation subclass
 */
// TESTCASE NUMBER: 1
abstract class Counter118 {
    abstract fun next118(): Int
}

class CounterImpl118 : Counter118() {
    private var value = 41
    override fun next118(): Int = ++value
}

fun box(): String = if (CounterImpl118().next118() == 42) "OK" else "NOK"

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions, value-equality-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: ValueBox == compares instances using custom equals implementation
 */

// TESTCASE NUMBER: 1

class ValueBox(val value: Int) {
    override fun equals(other: Any?): Boolean = other is ValueBox && value == other.value
    override fun hashCode(): Int = value
}

fun box(): String {
    if (ValueBox(1) == ValueBox(1) && ValueBox(1) != ValueBox(2)) return "OK"
    return "NOK"
}

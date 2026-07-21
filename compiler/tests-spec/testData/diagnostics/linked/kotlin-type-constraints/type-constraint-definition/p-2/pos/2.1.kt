// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, type-constraint-definition -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: fixed type variable in class body is not a free inference variable at use site
 * HELPERS: checkType
 */

class Cell131<T>(private val value: T) {
    fun read(): T = value
}

// TESTCASE NUMBER: 1
fun case_1() {
    val cell = Cell131(42)
    checkSubtype<Int>(cell.read())
}

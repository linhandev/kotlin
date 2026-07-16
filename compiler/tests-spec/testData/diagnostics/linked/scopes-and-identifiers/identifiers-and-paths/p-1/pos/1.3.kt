// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, identifiers-and-paths -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: this.value in Counter642.increment reads instance field
 */

// TESTCASE NUMBER: 1
class Counter642(private var value: Int) {
    fun increment(): Int {
        value += 1
        return this.value
    }
}

fun case1(c: Counter642): Int = c.increment()

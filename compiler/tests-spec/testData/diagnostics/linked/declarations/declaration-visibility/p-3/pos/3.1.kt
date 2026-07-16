// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: private visibility lifts variance conflicts and nested scopes access outer private members
 */

// TESTCASE NUMBER: 1
class Out<out T> {
    private fun store(value: T) {}
}

// TESTCASE NUMBER: 2
class Outer {
    private val secret = 1
    inner class Inner {
        fun read(): Int = secret
    }
}

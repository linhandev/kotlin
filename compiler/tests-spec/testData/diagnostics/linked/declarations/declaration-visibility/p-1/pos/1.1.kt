// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: declarations are public by default and overriding declarations inherit visibility
 */

// TESTCASE NUMBER: 1
fun publicByDefault(): Int = 1

fun usePublic(): Int = publicByDefault()

// TESTCASE NUMBER: 2
open class Base {
    protected open fun guarded() {}
}

class Derived : Base() {
    override fun guarded() {
        super.guarded()
    }
}

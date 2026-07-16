// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: superclass init runs before class body property and init blocks
 */

// TESTCASE NUMBER: 1
open class Base {
    val step = "base"
}

class Child(val n: Int) : Base() {
    val p = n
    init {
        val ok = step == "base" && p == n
    }
}

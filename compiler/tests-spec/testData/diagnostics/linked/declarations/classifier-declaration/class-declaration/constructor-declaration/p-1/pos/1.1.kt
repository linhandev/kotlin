// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: secondary constructors delegate to primary constructor via this()
 */

// TESTCASE NUMBER: 1
open class Base

class Primary(val s: String) : Base() {
    constructor(i: Int) : this(i.toString())
    constructor(d: Double) : this(d.toInt())
}

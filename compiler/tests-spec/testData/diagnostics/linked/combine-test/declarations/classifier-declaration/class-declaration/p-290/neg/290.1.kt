// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 290 -> sentence 290
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 290 -> sentence 290
 *                declarations, classifier-declaration, local-class-declaration -> paragraph 290 -> sentence 290
 * NUMBER: 1
 * DESCRIPTION: private members of a local class are invisible to the enclosing function body outside the local class (INVISIBLE_MEMBER); covers primary-constructor property, member function, and body property; contrasts with next-point public local members
 */

// TESTCASE NUMBER: 1
fun case1(): Int {
    class Local(private val v: Int)
    return Local(1).<!INVISIBLE_MEMBER!>v<!>
}

// TESTCASE NUMBER: 2
fun case2(): Int {
    class Local {
        private fun code(): Int = 2
    }
    return Local().<!INVISIBLE_MEMBER!>code<!>()
}

// TESTCASE NUMBER: 3
fun case3(): String {
    class Local {
        private val hidden: String = "x"
    }
    return Local().<!INVISIBLE_MEMBER!>hidden<!>
}

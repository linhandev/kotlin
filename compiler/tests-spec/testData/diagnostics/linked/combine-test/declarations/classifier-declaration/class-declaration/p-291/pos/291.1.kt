// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 291 -> sentence 291
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 291 -> sentence 291
 *                declarations, classifier-declaration, local-class-declaration -> paragraph 291 -> sentence 291
 * NUMBER: 1
 * DESCRIPTION: precise types when public members of a local class are accessed in the declaring function body
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    class Local(val v: Int)
    val x = Local(1).v
    x checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    class Local {
        fun code(): Int = 2
    }
    Local().code() checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    class Local {
        val label: String = "L"
        fun text(): String = label
    }
    Local().text() checkType { check<String>() }
    Local().label checkType { check<String>() }
}

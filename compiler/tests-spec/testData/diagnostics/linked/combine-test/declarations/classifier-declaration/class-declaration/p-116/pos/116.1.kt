// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 116 -> sentence 116
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 116 -> sentence 116
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 116 -> sentence 116
 * NUMBER: 1
 * DESCRIPTION: Java-style class with only secondary constructor delegating to implicit primary constructor type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Legacy() {
    var field: String = ""

    constructor(s: String) : this() {
        field = s
    }
}

fun case1() {
    val viaSecondary = Legacy("a")
    viaSecondary checkType { check<Legacy>() }
    viaSecondary.field checkType { check<String>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaSecondaryOther = Legacy("bb")
    viaSecondaryOther checkType { check<Legacy>() }
    viaSecondaryOther.field checkType { check<String>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val viaPrimary = Legacy()
    viaPrimary checkType { check<Legacy>() }
    viaPrimary.field checkType { check<String>() }
}

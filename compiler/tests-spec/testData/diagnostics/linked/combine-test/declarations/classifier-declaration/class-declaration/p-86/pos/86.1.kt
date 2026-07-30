// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 86 -> sentence 86
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 86 -> sentence 86
 * NUMBER: 1
 * DESCRIPTION: primary default params with secondary constructor
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class User(val name: String, val age: Int = 0) {
    constructor(name: String) : this(name, 0)
}

fun case1() {
    checkSubtype<Int>(User("Ann").age)
}


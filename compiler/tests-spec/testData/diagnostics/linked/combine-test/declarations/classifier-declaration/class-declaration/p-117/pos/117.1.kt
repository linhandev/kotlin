// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 117 -> sentence 117
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 117 -> sentence 117
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 117 -> sentence 117
 * NUMBER: 1
 * DESCRIPTION: property initializer may use primary constructor parameter type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class User(val name: String) {
    val upper = name.uppercase()
}

fun case1() {
    val viaLower = User("a")
    viaLower checkType { check<User>() }
    viaLower.name checkType { check<String>() }
    viaLower.upper checkType { check<String>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaMixed = User("AbC")
    viaMixed checkType { check<User>() }
    viaMixed.name checkType { check<String>() }
    viaMixed.upper checkType { check<String>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val viaEmpty = User("")
    viaEmpty checkType { check<User>() }
    viaEmpty.name checkType { check<String>() }
    viaEmpty.upper checkType { check<String>() }
}

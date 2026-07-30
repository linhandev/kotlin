// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 106 -> sentence 106
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 106 -> sentence 106
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 106 -> sentence 106
 *                declarations, function-declaration -> paragraph 106 -> sentence 106
 * NUMBER: 1
 * DESCRIPTION: secondary constructor with default parameter delegates to primary constructor type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class User(val name: String, val age: Int) {
    constructor(name: String = "guest") : this(name, 0)
}

fun case1() {
    val viaDefault = User()
    viaDefault checkType { check<User>() }
    viaDefault.name checkType { check<String>() }
    viaDefault.age checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaExplicit = User("Ann")
    viaExplicit checkType { check<User>() }
    viaExplicit.name checkType { check<String>() }
    viaExplicit.age checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val viaPrimary = User("Bob", 25)
    viaPrimary checkType { check<User>() }
    viaPrimary.name checkType { check<String>() }
    viaPrimary.age checkType { check<Int>() }
}

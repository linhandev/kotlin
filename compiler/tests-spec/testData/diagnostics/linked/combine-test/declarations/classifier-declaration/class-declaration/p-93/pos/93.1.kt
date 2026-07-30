// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 93 -> sentence 93
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 93 -> sentence 93
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 93 -> sentence 93
 * NUMBER: 1
 * DESCRIPTION: multiple secondary constructors delegate to primary constructor separately type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class User(val name: String, val age: Int) {
    constructor(name: String) : this(name, 0)
    constructor() : this("guest", 0)
}

fun case1() {
    val viaNoArg = User()
    viaNoArg checkType { check<User>() }
    viaNoArg.name checkType { check<String>() }
    viaNoArg.age checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaSingleArg = User("Ann")
    viaSingleArg checkType { check<User>() }
    viaSingleArg.name checkType { check<String>() }
    viaSingleArg.age checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val viaPrimary = User("Bob", 25)
    viaPrimary checkType { check<User>() }
    viaPrimary.name checkType { check<String>() }
    viaPrimary.age checkType { check<Int>() }
}

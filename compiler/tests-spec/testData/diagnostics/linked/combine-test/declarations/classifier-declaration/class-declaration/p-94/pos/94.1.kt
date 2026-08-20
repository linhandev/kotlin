// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 94 -> sentence 94
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 94 -> sentence 94
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 94 -> sentence 94
 * NUMBER: 1
 * DESCRIPTION: secondary constructor chains delegation through overload to primary constructor type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class User(val name: String, val age: Int, val tag: String) {
    constructor(n: String, a: Int) : this(n, a, "")
    constructor(n: String) : this(n, 0)
}

fun case1() {
    val viaChained = User("Ann")
    viaChained checkType { check<User>() }
    viaChained.name checkType { check<String>() }
    viaChained.age checkType { check<Int>() }
    viaChained.tag checkType { check<String>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaTwoArg = User("Bob", 5)
    viaTwoArg checkType { check<User>() }
    viaTwoArg.name checkType { check<String>() }
    viaTwoArg.age checkType { check<Int>() }
    viaTwoArg.tag checkType { check<String>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val viaPrimary = User("Cat", 3, "vip")
    viaPrimary checkType { check<User>() }
    viaPrimary.name checkType { check<String>() }
    viaPrimary.age checkType { check<Int>() }
    viaPrimary.tag checkType { check<String>() }
}

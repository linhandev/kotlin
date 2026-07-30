// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 91 -> sentence 91
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 91 -> sentence 91
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 91 -> sentence 91
 *                declarations, property-declaration -> paragraph 91 -> sentence 91
 * NUMBER: 1
 * DESCRIPTION: secondary constructor this() fills primary params that drive derived property initializer type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class User(name: String, age: Int) {
    val display: String = "$name#$age"
    constructor(name: String) : this(name, 0)
}

fun case1() {
    val viaSecondary = User("Ann")
    viaSecondary checkType { check<User>() }
    viaSecondary.display checkType { check<String>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaPrimary = User("Bob", 25)
    viaPrimary checkType { check<User>() }
    viaPrimary.display checkType { check<String>() }
}

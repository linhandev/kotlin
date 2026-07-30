// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 107 -> sentence 107
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 107 -> sentence 107
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 107 -> sentence 107
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 107 -> sentence 107
 * NUMBER: 1
 * DESCRIPTION: secondary constructor with reordered parameters delegates via named arguments at call site type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class User(val name: String, val age: Int) {
    constructor(years: Int, label: String) : this(label, years)
}

fun case1() {
    val viaNamed = User(years = 2, label = "Ann")
    viaNamed checkType { check<User>() }
    viaNamed.name checkType { check<String>() }
    viaNamed.age checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaPositional = User(3, "Bob")
    viaPositional checkType { check<User>() }
    viaPositional.name checkType { check<String>() }
    viaPositional.age checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val viaPrimary = User("Cat", 4)
    viaPrimary checkType { check<User>() }
    viaPrimary.name checkType { check<String>() }
    viaPrimary.age checkType { check<Int>() }
}

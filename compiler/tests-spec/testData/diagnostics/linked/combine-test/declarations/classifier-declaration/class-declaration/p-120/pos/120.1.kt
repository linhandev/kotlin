// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 120 -> sentence 120
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 120 -> sentence 120
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 120 -> sentence 120
 * NUMBER: 1
 * DESCRIPTION: property initializers run before init blocks and in source order relative to later properties type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Seq {
    val log = StringBuilder().apply { append("A") }

    init {
        log.append("B")
    }

    val tail = buildString { append("C") }
}

fun case1() {
    val instance = Seq()
    instance checkType { check<Seq>() }
    instance.log checkType { check<StringBuilder>() }
    instance.tail checkType { check<String>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val logPart = Seq().log.toString()
    logPart checkType { check<String>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val tailPart = Seq().tail
    tailPart checkType { check<String>() }
}

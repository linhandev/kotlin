// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 110 -> sentence 110
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 110 -> sentence 110
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 110 -> sentence 110
 *                declarations, classifier-declaration, classifier-declaration-scopes -> paragraph 110 -> sentence 110
 *                declarations, declaration-visibility -> paragraph 110 -> sentence 110
 * NUMBER: 1
 * DESCRIPTION: public secondary constructors expose creation while primary constructor stays private type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Service private constructor(val port: Int) {
    constructor() : this(8080)

    constructor(port: Int, tagged: Boolean) : this(port)
}

fun case1() {
    val viaDefault = Service()
    viaDefault checkType { check<Service>() }
    viaDefault.port checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaTagged = Service(9090, true)
    viaTagged checkType { check<Service>() }
    viaTagged.port checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val viaTaggedOther = Service(3000, false)
    viaTaggedOther checkType { check<Service>() }
    viaTaggedOther.port checkType { check<Int>() }
}

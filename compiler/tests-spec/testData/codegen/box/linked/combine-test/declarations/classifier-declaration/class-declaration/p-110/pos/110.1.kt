// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 110 -> sentence 110
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 110 -> sentence 110
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 110 -> sentence 110
 *                declarations, classifier-declaration, classifier-declaration-scopes -> paragraph 110 -> sentence 110
 *                declarations, declaration-visibility -> paragraph 110 -> sentence 110
 * NUMBER: 1
 * DESCRIPTION: public secondary constructors expose creation while primary constructor stays private in class declaration
 */

// TESTCASE NUMBER: 1
class Service private constructor(val port: Int) {
    constructor() : this(8080)

    constructor(port: Int, tagged: Boolean) : this(port)
}

fun viaDefaultSecondary(): Int = Service().port

fun viaTaggedSecondary(): Int = Service(9090, true).port

fun box(): String {
    if (viaDefaultSecondary() != 8080) return "NOK: default port"
    if (viaTaggedSecondary() != 9090) return "NOK: tagged port"
    return "OK"
}

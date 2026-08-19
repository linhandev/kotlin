// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 116 -> sentence 116
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 116 -> sentence 116
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 116 -> sentence 116
 * NUMBER: 1
 * DESCRIPTION: Java-style class with only secondary constructor delegating to implicit primary constructor in class declaration
 */

// TESTCASE NUMBER: 1
class Legacy() {
    var field: String = ""

    constructor(s: String) : this() {
        field = s
    }
}

fun viaSecondary(): String = Legacy("a").field

fun viaSecondaryOther(): String = Legacy("bb").field

fun viaPrimary(): String = Legacy().field

fun box(): String {
    if (viaSecondary() != "a") return "NOK: secondary"
    if (viaSecondaryOther() != "bb") return "NOK: secondary other"
    if (viaPrimary() != "") return "NOK: primary"
    return "OK"
}

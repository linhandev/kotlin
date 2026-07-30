// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 128 -> sentence 128
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 128 -> sentence 128
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 128 -> sentence 128
 * NUMBER: 1
 * DESCRIPTION: init block may read primary constructor parameters when assigning properties in class declaration
 */

// TESTCASE NUMBER: 1
class Wrap(val raw: String) {
    val norm: String

    init {
        norm = raw.trim()
    }
}

class Prefix(val raw: String) {
    val tagged: String

    init {
        tagged = "x:$raw"
    }
}

class Length(val raw: String) {
    val size: Int

    init {
        size = raw.length
    }
}

fun viaSpaces(): String = Wrap(" a ").norm

fun viaTabs(): String = Wrap("\tb\t").norm

fun viaPrefix(): String = Prefix("ok").tagged

fun viaLength(): Int = Length("hi").size

fun box(): String {
    if (viaSpaces() != "a") return "NOK: spaces"
    if (viaTabs() != "b") return "NOK: tabs"
    if (viaPrefix() != "x:ok") return "NOK: prefix"
    if (viaLength() != 2) return "NOK: length"
    return "OK"
}

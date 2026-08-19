// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 119 -> sentence 119
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 119 -> sentence 119
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 119 -> sentence 119
 * NUMBER: 1
 * DESCRIPTION: multiple init blocks execute in source order after property initializers in class declaration
 */

// TESTCASE NUMBER: 1
class ChronLog {
    val buf = StringBuilder().apply { append("P") }

    init {
        buf.append("1")
    }

    init {
        buf.append("2")
    }
}

class TaggedChron(val tag: String) {
    val buf = StringBuilder().apply { append(tag) }

    init {
        buf.append("1")
    }

    init {
        buf.append("2")
    }
}

fun defaultBuf(): String = ChronLog().buf.toString()

fun taggedAlpha(): String = TaggedChron("A").buf.toString()

fun taggedBeta(): String = TaggedChron("B").buf.toString()

fun box(): String {
    if (defaultBuf() != "P12") return "NOK: default"
    if (taggedAlpha() != "A12") return "NOK: alpha"
    if (taggedBeta() != "B12") return "NOK: beta"
    return "OK"
}

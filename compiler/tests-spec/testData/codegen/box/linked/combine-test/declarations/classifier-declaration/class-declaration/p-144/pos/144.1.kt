// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 144 -> sentence 144
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 144 -> sentence 144
 *                declarations, declarations-with-type-parameters -> paragraph 144 -> sentence 144
 * NUMBER: 1
 * DESCRIPTION: generic class init block may use type-parameter-typed constructor properties in class declaration
 */

// TESTCASE NUMBER: 1
class TaggedBox<T>(val v: T) {
    val tag: String

    init {
        tag = v.toString()
    }
}

// TESTCASE NUMBER: 2
class LengthHolder<T : CharSequence>(val v: T) {
    val len: Int
    val upper: String

    init {
        len = v.length
        upper = v.toString().uppercase()
    }
}

// TESTCASE NUMBER: 3
class PairTag<A, B>(val left: A, val right: B) {
    val combined: String

    init {
        combined = "${left}-${right}"
    }
}

fun viaIntBox(): String = TaggedBox(1).tag

fun viaStringBox(): String = TaggedBox("hi").tag

fun viaLength(): Pair<Int, String> {
    val h = LengthHolder("ab")
    return h.len to h.upper
}

fun viaPairTag(): String = PairTag(3, "x").combined

fun box(): String {
    if (viaIntBox() != "1") return "NOK: int-box"
    if (viaStringBox() != "hi") return "NOK: string-box"
    if (viaLength() != (2 to "AB")) return "NOK: length"
    if (viaPairTag() != "3-x") return "NOK: pair"
    if (TaggedBox(true).tag != "true") return "NOK: bool-box"
    if (LengthHolder("xyz").len != 3) return "NOK: length-xyz"
    return "OK"
}

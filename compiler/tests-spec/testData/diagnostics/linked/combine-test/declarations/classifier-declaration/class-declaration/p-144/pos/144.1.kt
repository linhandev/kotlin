// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 144 -> sentence 144
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 144 -> sentence 144
 *                declarations, declarations-with-type-parameters -> paragraph 144 -> sentence 144
 * NUMBER: 1
 * DESCRIPTION: generic class init block may use type-parameter-typed constructor properties type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class TaggedBox<T>(val v: T) {
    val tag: String

    init {
        tag = v.toString()
    }
}

fun case1() {
    val intBox = TaggedBox(1)
    intBox checkType { check<TaggedBox<Int>>() }
    intBox.v checkType { check<Int>() }
    intBox.tag checkType { check<String>() }
    val stringBox = TaggedBox("hi")
    stringBox checkType { check<TaggedBox<String>>() }
    stringBox.v checkType { check<String>() }
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

fun case2() {
    val h = LengthHolder("ab")
    h checkType { check<LengthHolder<String>>() }
    h.v checkType { check<String>() }
    h.len checkType { check<Int>() }
    h.upper checkType { check<String>() }
}

// TESTCASE NUMBER: 3
class PairTag<A, B>(val left: A, val right: B) {
    val combined: String

    init {
        combined = "${left}-${right}"
    }
}

fun case3() {
    val p = PairTag(3, "x")
    p checkType { check<PairTag<Int, String>>() }
    p.left checkType { check<Int>() }
    p.right checkType { check<String>() }
    p.combined checkType { check<String>() }
}

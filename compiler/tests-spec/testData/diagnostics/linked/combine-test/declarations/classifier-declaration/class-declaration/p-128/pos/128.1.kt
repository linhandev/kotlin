// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 128 -> sentence 128
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 128 -> sentence 128
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 128 -> sentence 128
 * NUMBER: 1
 * DESCRIPTION: init block may read primary constructor parameters type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Wrap(val raw: String) {
    val norm: String

    init {
        norm = raw.trim()
    }
}

fun case1() {
    val viaSpaces = Wrap(" a ")
    viaSpaces checkType { check<Wrap>() }
    viaSpaces.raw checkType { check<String>() }
    viaSpaces.norm checkType { check<String>() }
}

// TESTCASE NUMBER: 2
class Prefix(val raw: String) {
    val tagged: String

    init {
        tagged = "x:$raw"
    }
}

fun case2() {
    val viaPrefix = Prefix("ok")
    viaPrefix checkType { check<Prefix>() }
    viaPrefix.raw checkType { check<String>() }
    viaPrefix.tagged checkType { check<String>() }
}

// TESTCASE NUMBER: 3
class Length(val raw: String) {
    val size: Int

    init {
        size = raw.length
    }
}

fun case3() {
    val viaLength = Length("hi")
    viaLength checkType { check<Length>() }
    viaLength.raw checkType { check<String>() }
    viaLength.size checkType { check<Int>() }
}

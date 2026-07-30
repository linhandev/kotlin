// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 119 -> sentence 119
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 119 -> sentence 119
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 119 -> sentence 119
 * NUMBER: 1
 * DESCRIPTION: multiple init blocks execute in source order after property initializers type inference in class declaration
 * HELPERS: checkType
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

fun case1() {
    val defaultBuf = ChronLog().buf.toString()
    defaultBuf checkType { check<String>() }
}

// TESTCASE NUMBER: 2
class TaggedChron(val tag: String) {
    val buf = StringBuilder().apply { append(tag) }

    init {
        buf.append("1")
    }

    init {
        buf.append("2")
    }
}

fun case2() {
    val taggedAlpha = TaggedChron("A").buf.toString()
    taggedAlpha checkType { check<String>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val taggedBeta = TaggedChron("B").buf.toString()
    taggedBeta checkType { check<String>() }
}

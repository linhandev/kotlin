// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 127 -> sentence 127
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 127 -> sentence 127
 * NUMBER: 1
 * DESCRIPTION: init block may call instance methods type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Svc {
    var observed = -1

    fun ping(): Int = 1

    init {
        observed = ping()
    }
}

fun case1() {
    val viaSvc = Svc()
    viaSvc checkType { check<Svc>() }
    viaSvc.observed checkType { check<Int>() }
    viaSvc.ping() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
class Tag {
    var label = ""

    fun mark(): String = "ready"

    init {
        label = mark()
    }
}

fun case2() {
    val viaTag = Tag()
    viaTag checkType { check<Tag>() }
    viaTag.label checkType { check<String>() }
    viaTag.mark() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
class Accum {
    var sum = 0

    fun step(): Int = 10

    init {
        sum = step() + step()
    }
}

fun case3() {
    val viaAccum = Accum()
    viaAccum checkType { check<Accum>() }
    viaAccum.sum checkType { check<Int>() }
    viaAccum.step() checkType { check<Int>() }
}

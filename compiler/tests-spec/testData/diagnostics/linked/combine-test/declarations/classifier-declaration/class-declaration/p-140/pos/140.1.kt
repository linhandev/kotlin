// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 140 -> sentence 140
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 140 -> sentence 140
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 140 -> sentence 140
 *                declarations, property-declaration -> paragraph 140 -> sentence 140
 * NUMBER: 1
 * DESCRIPTION: data class generated properties still allow additional init block validation type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Point(val x: Int, val y: Int) {
    init {
        require(x >= 0)
    }
}

fun case1() {
    val p = Point(1, 2)
    p checkType { check<Point>() }
    p.x checkType { check<Int>() }
    p.y checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
data class Named(val label: String, val rank: Int) {
    init {
        require(label.isNotEmpty())
        require(rank > 0)
    }
}

fun case2() {
    val n = Named("ok", 3)
    n checkType { check<Named>() }
    n.label checkType { check<String>() }
    n.rank checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
data class Box(val value: Int) {
    val doubled: Int

    init {
        require(value != 0)
        doubled = value * 2
    }
}

fun case3() {
    val b = Box(4)
    b checkType { check<Box>() }
    b.value checkType { check<Int>() }
    b.doubled checkType { check<Int>() }
}

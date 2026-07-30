// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 140 -> sentence 140
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 140 -> sentence 140
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 140 -> sentence 140
 *                declarations, property-declaration -> paragraph 140 -> sentence 140
 * NUMBER: 1
 * DESCRIPTION: data class generated properties still allow additional init block validation in class declaration
 */

// TESTCASE NUMBER: 1
data class Point(val x: Int, val y: Int) {
    init {
        require(x >= 0)
    }
}

// TESTCASE NUMBER: 2
data class Named(val label: String, val rank: Int) {
    init {
        require(label.isNotEmpty())
        require(rank > 0)
    }
}

// TESTCASE NUMBER: 3
data class Box(val value: Int) {
    val doubled: Int

    init {
        require(value != 0)
        doubled = value * 2
    }
}

fun viaPoint(): Point = Point(1, 2)

fun viaNamed(): Named = Named("ok", 3)

fun viaBox(): Int = Box(4).doubled

fun pointRejectsNegative(): Boolean {
    return try {
        Point(-1, 0)
        false
    } catch (e: IllegalArgumentException) {
        true
    }
}

fun namedRejectsBlank(): Boolean {
    return try {
        Named("", 1)
        false
    } catch (e: IllegalArgumentException) {
        true
    }
}

fun boxRejectsZero(): Boolean {
    return try {
        Box(0)
        false
    } catch (e: IllegalArgumentException) {
        true
    }
}

fun box(): String {
    val p = viaPoint()
    if (p.x != 1 || p.y != 2) return "NOK: point"
    if (p.copy(x = 5).x != 5) return "NOK: copy"
    val n = viaNamed()
    if (n.label != "ok" || n.rank != 3) return "NOK: named"
    if (viaBox() != 8) return "NOK: box"
    if (!pointRejectsNegative()) return "NOK: point-neg"
    if (!namedRejectsBlank()) return "NOK: named-blank"
    if (!boxRejectsZero()) return "NOK: box-zero"
    return "OK"
}

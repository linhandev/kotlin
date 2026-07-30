// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 139 -> sentence 139
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 139 -> sentence 139
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 139 -> sentence 139
 * NUMBER: 1
 * DESCRIPTION: all init blocks complete before secondary constructor body after this() in class declaration
 */

// TESTCASE NUMBER: 1
class Seq() {
    val a = mutableListOf<Int>().apply { add(0) }

    init {
        a += 1
    }

    init {
        a += 2
    }

    constructor(tag: String) : this() {
        a += 3
    }
}

// TESTCASE NUMBER: 2
class Steps() {
    val log = mutableListOf<String>().apply { add("P") }

    init {
        log += "I1"
    }

    init {
        log += "I2"
    }

    constructor(tag: String) : this() {
        log += "S"
    }
}

// TESTCASE NUMBER: 3
class Acc() {
    var total = 1

    init {
        total += 10
    }

    init {
        total *= 2
    }

    constructor(tag: String) : this() {
        total += 100
    }
}

fun viaSeqSecondary(): List<Int> = Seq("t").a

fun viaSeqPrimary(): List<Int> = Seq().a

fun viaStepsSecondary(): List<String> = Steps("u").log

fun viaAccSecondary(): Int = Acc("v").total

fun box(): String {
    if (viaSeqSecondary() != listOf(0, 1, 2, 3)) return "NOK: seq-secondary"
    if (viaSeqPrimary() != listOf(0, 1, 2)) return "NOK: seq-primary"
    if (viaStepsSecondary() != listOf("P", "I1", "I2", "S")) return "NOK: steps"
    if (viaAccSecondary() != 122) return "NOK: acc"
    return "OK"
}

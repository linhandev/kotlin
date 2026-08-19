// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 160 -> sentence 160
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 160 -> sentence 160
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 160 -> sentence 160
 *                inheritance, inheriting -> paragraph 160 -> sentence 160
 * NUMBER: 1
 * DESCRIPTION: after constructor delegation, superclass primary-constructor-driven property and init complete before subclass property and init in class declaration
 */

// TESTCASE NUMBER: 1
open class Base(seed: String) {
    val log = StringBuilder().apply { append(seed) }
    init {
        log.append("I")
    }
}

class Child(seed: String) : Base(seed) {
    val mid = log.append("P").toString().length
    init {
        log.append("C")
    }
}

// TESTCASE NUMBER: 2
open class LayerA(mark: Char) {
    val steps = mutableListOf<Char>()
    init {
        steps += mark
        steps += 'A'
    }
}

class LayerB(mark: Char) : LayerA(mark) {
    init {
        steps += 'B'
    }
}

// TESTCASE NUMBER: 3
open class Root(val n: Int) {
    val doubled: Int
    init {
        doubled = n * 2
    }
}

class Leaf(n: Int, val tag: String) : Root(n) {
    val summary: String
    init {
        summary = "$doubled:$tag"
    }
}

fun viaChild(seed: String): String = Child(seed).log.toString()

fun viaLayer(mark: Char): List<Char> = LayerB(mark).steps

fun viaLeaf(n: Int, tag: String): Pair<Int, String> {
    val leaf = Leaf(n, tag)
    return leaf.doubled to leaf.summary
}

fun box(): String {
    if (viaChild("B") != "BIPC") return "NOK: child-B"
    if (viaChild("X") != "XIPC") return "NOK: child-X"
    if (Child("B").mid <= 1) return "NOK: mid-after-base"

    if (viaLayer('Z') != listOf('Z', 'A', 'B')) return "NOK: layer-Z"
    if (viaLayer('Q') != listOf('Q', 'A', 'B')) return "NOK: layer-Q"

    if (viaLeaf(3, "t") != (6 to "6:t")) return "NOK: leaf-3"
    if (viaLeaf(5, "ok") != (10 to "10:ok")) return "NOK: leaf-5"
    return "OK"
}

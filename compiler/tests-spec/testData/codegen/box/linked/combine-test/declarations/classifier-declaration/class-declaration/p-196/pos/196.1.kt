// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 196 -> sentence 196
 * PRIMARY LINKS: inheritance, overriding -> paragraph 196 -> sentence 196
 *                inheritance, inheriting -> paragraph 196 -> sentence 196
 *                expressions, super-forms -> paragraph 196 -> sentence 196
 * NUMBER: 1
 * DESCRIPTION: overriding equals/hashCode in a class hierarchy participates in structural equality, with Child equals composing super.equals
 */

// TESTCASE NUMBER: 1
open class Base(val id: Int) {
    override fun equals(other: Any?): Boolean = other is Base && id == other.id
    override fun hashCode(): Int = id
}

class Child(id: Int, val tag: String) : Base(id) {
    override fun equals(other: Any?): Boolean =
        other is Child && super.equals(other) && tag == other.tag

    override fun hashCode(): Int = 31 * super.hashCode() + tag.hashCode()
}

// TESTCASE NUMBER: 2
open class Point(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean =
        other is Point && x == other.x && y == other.y

    override fun hashCode(): Int = 31 * x + y
}

class LabeledPoint(x: Int, y: Int, val label: String) : Point(x, y) {
    override fun equals(other: Any?): Boolean =
        other is LabeledPoint && super.equals(other) && label == other.label

    override fun hashCode(): Int = 31 * super.hashCode() + label.hashCode()
}

// TESTCASE NUMBER: 3
open class Key(val code: Int) {
    override fun equals(other: Any?): Boolean = other is Key && code == other.code
    override fun hashCode(): Int = code
    fun sameAs(other: Key): Boolean = this == other
}

class TaggedKey(code: Int, val tag: String) : Key(code) {
    override fun equals(other: Any?): Boolean =
        other is TaggedKey && super.equals(other) && tag == other.tag

    override fun hashCode(): Int = 31 * super.hashCode() + tag.hashCode()
}

fun box(): String {
    if (Child(1, "a") != Child(1, "a")) return "NOK: child-eq"
    if (Child(1, "a") == Child(1, "b")) return "NOK: child-tag"
    if (Child(1, "a") == Child(2, "a")) return "NOK: child-id"
    if (Child(1, "a").hashCode() != Child(1, "a").hashCode()) return "NOK: child-hash"
    if (Base(1) != Base(1)) return "NOK: base-eq"
    if (Child(1, "a") == Base(1)) return "NOK: child-vs-base"

    if (LabeledPoint(1, 2, "p") != LabeledPoint(1, 2, "p")) return "NOK: labeled-eq"
    if (LabeledPoint(1, 2, "p") == LabeledPoint(1, 2, "q")) return "NOK: labeled-label"
    if (LabeledPoint(1, 2, "p") == Point(1, 2)) return "NOK: labeled-vs-point"
    if (LabeledPoint(1, 2, "p").hashCode() != LabeledPoint(1, 2, "p").hashCode()) return "NOK: labeled-hash"

    val k1 = TaggedKey(5, "t")
    val k2 = TaggedKey(5, "t")
    val k3 = TaggedKey(5, "u")
    if (!k1.sameAs(k2)) return "NOK: tagged-sameAs"
    if (k1.sameAs(k3)) return "NOK: tagged-diff-tag"
    if (k1 == Key(5)) return "NOK: tagged-vs-key"
    return "OK"
}

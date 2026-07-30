// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 196 -> sentence 196
 * PRIMARY LINKS: inheritance, overriding -> paragraph 196 -> sentence 196
 *                inheritance, inheriting -> paragraph 196 -> sentence 196
 *                expressions, super-forms -> paragraph 196 -> sentence 196
 * NUMBER: 1
 * DESCRIPTION: type inference for equals/hashCode overrides composed across a class hierarchy in a class declaration
 * HELPERS: checkType
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

fun case1() {
    val c = Child(1, "a")
    c checkType { check<Child>() }
    checkSubtype<Base>(c)
    c.equals(Child(1, "a")) checkType { check<Boolean>() }
    c.hashCode() checkType { check<Int>() }
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

fun case2() {
    val p = LabeledPoint(1, 2, "p")
    p checkType { check<LabeledPoint>() }
    checkSubtype<Point>(p)
    p.hashCode() checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
open class Key(val code: Int) {
    override fun equals(other: Any?): Boolean = other is Key && code == other.code
    override fun hashCode(): Int = code
}

class TaggedKey(code: Int, val tag: String) : Key(code) {
    override fun equals(other: Any?): Boolean =
        other is TaggedKey && super.equals(other) && tag == other.tag

    override fun hashCode(): Int = 31 * super.hashCode() + tag.hashCode()
}

fun case3() {
    val k = TaggedKey(5, "t")
    k checkType { check<TaggedKey>() }
    checkSubtype<Key>(k)
    (k == TaggedKey(5, "t")) checkType { check<Boolean>() }
}

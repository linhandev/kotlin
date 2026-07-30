// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 172 -> sentence 172
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 172 -> sentence 172
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 172 -> sentence 172
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 172 -> sentence 172
 * NUMBER: 1
 * DESCRIPTION: superclass init blocks run before subclass init blocks after constructor delegation in class declaration
 */

// TESTCASE NUMBER: 1
open class Base {
    val buf = StringBuilder()
    init {
        buf.append('B')
    }
}

class Child : Base() {
    init {
        buf.append('C')
    }
}

// TESTCASE NUMBER: 2
open class Tagged(tag: Char) {
    val buf = StringBuilder()
    init {
        buf.append(tag)
    }
}

class TaggedChild(c: Char) : Tagged(c) {
    init {
        buf.append('C')
    }
}

// TESTCASE NUMBER: 3
open class Grand {
    val buf = StringBuilder()
    init {
        buf.append('G')
    }
}

open class Mid(v: Int) : Grand() {
    init {
        buf.append('M')
        buf.append(v.toString())
    }
}

class Leaf(v: Int) : Mid(v) {
    init {
        buf.append('L')
    }
}

fun viaChild(): String = Child().buf.toString()

fun viaTagged(): Pair<String, String> =
    TaggedChild('B').buf.toString() to TaggedChild('X').buf.toString()

fun viaLeaf(): Pair<String, String> =
    Leaf(2).buf.toString() to Leaf(9).buf.toString()

fun box(): String {
    if (viaChild() != "BC") return "NOK: child"
    if (Child().buf.toString() != "BC") return "NOK: child-direct"

    if (viaTagged() != ("BC" to "XC")) return "NOK: tagged"
    if (TaggedChild('Z').buf.toString() != "ZC") return "NOK: tagged-z"

    if (viaLeaf() != ("GM2L" to "GM9L")) return "NOK: leaf"
    if ((Leaf(1) as Grand).buf.toString() != "GM1L") return "NOK: leaf-as-grand"
    return "OK"
}

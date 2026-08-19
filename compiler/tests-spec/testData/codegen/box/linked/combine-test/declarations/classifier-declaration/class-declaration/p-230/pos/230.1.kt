// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 230 -> sentence 230
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 230 -> sentence 230
 *                inheritance, overriding -> paragraph 230 -> sentence 230
 *                expressions, object-literals -> paragraph 230 -> sentence 230
 * NUMBER: 1
 * DESCRIPTION: an anonymous object implementing dual conflicting interface defaults must explicitly override; each expression yields a fresh instance with the override value; contrasts with p-229 named-object singleton and with p-223 named class instances
 */

// TESTCASE NUMBER: 1
interface LeftFun {
    fun f(): Int = 1
}

interface RightFun {
    fun f(): Int = 2
}

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tag(): String = "L"
}

interface RightTag {
    fun tag(): String = "R"
}

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Int get() = 3
}

interface RightVal {
    val n: Int get() = 4
}

fun box(): String {
    val a = object : LeftFun, RightFun {
        override fun f(): Int = 7
    }
    if (a.f() != 7) return "NOK: anon-fun"
    val asLeft: LeftFun = a
    if (asLeft.f() != 7) return "NOK: via-left-fun"
    val asRight: RightFun = a
    if (asRight.f() != 7) return "NOK: via-right-fun"
    if ((object : LeftFun, RightFun { override fun f(): Int = 7 }).f() != 7) return "NOK: inline-anon-fun"
    if (object : LeftFun {}.f() != 1) return "NOK: left-alone"
    if (object : RightFun {}.f() != 2) return "NOK: right-alone"

    val t = object : LeftTag, RightTag {
        override fun tag(): String = "ok"
    }
    if (t.tag() != "ok") return "NOK: anon-tag"
    val asLeftTag: LeftTag = t
    if (asLeftTag.tag() != "ok") return "NOK: via-left-tag"
    val asRightTag: RightTag = t
    if (asRightTag.tag() != "ok") return "NOK: via-right-tag"
    if ((object : LeftTag, RightTag { override fun tag(): String = "ok" }).tag() != "ok") return "NOK: inline-anon-tag"

    val v = object : LeftVal, RightVal {
        override val n: Int get() = super<LeftVal>.n + super<RightVal>.n
    }
    if (v.n != 7) return "NOK: anon-val"
    val asLeftVal: LeftVal = v
    if (asLeftVal.n != 7) return "NOK: via-left-val"
    val asRightVal: RightVal = v
    if (asRightVal.n != 7) return "NOK: via-right-val"

    val first: Any = object : LeftFun, RightFun {
        override fun f(): Int = 7
    }
    val second: Any = object : LeftFun, RightFun {
        override fun f(): Int = 7
    }
    if (first === second) return "NOK: expected-fresh-instances"
    return "OK"
}

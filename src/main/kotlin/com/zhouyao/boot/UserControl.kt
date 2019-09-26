package com.zhouyao.boot

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.RequestBody
import io.swagger.annotations.ApiImplicitParams
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping(value = "/users")     // 通过这里配置使下面的映射都在/users下，可去除
class UserControl {

    val userList: List<User>
        @ApiOperation(value = "获取用户列表", notes = "")
        @RequestMapping(value = [""], method = [RequestMethod.GET])
        get() = ArrayList<User>(users.values)

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "", method = [RequestMethod.POST])
    fun postUser(@RequestBody user: User): String {
//        users.put(user.getId(), user)
        return "success"
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = [RequestMethod.GET])
    fun getUser(@PathVariable id: Long?): Int {
//        return users.get(id)
        return 230
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"), ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User"))
    @RequestMapping(value = "/{id}", method = [RequestMethod.PUT])
    fun putUser(@PathVariable id: Long?, @RequestBody user: User): String {
//        val u = users.get(id)
//        u.setName(user.getName())
//        u.setAge(user.getAge())
//        users.put(id, u)
        return "success"
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = [RequestMethod.DELETE])
    fun deleteUser(@PathVariable id: Long?): String {
        users.remove(id)
        return "success"
    }

    companion object {

        internal var users = Collections.synchronizedMap(HashMap<Long, User>())
    }

}
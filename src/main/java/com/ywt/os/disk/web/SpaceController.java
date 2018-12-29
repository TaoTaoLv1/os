package com.ywt.os.disk.web;

import com.ywt.os.disk.common.Common;
import com.ywt.os.disk.entity.Doc;
import com.ywt.os.disk.param.ListResponse;
import com.ywt.os.disk.service.InitTask;
import com.ywt.os.disk.service.SpaceService;
import com.ywt.os.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private InitTask initTask;

    @PostMapping(value = "space/init")
    public ListResponse spaceInit() {
        initTask.initTask();
        ListResponse listResponse = new ListResponse();
        listResponse.setDisk(Common.disk);
        listResponse.setList(Common.list);
        listResponse.setDocList(Common.docList);
        return listResponse;

    }

    /**
     * 新增文件
     *
     * @param name
     * @param size
     * @return
     * @throws Exception
     */
    @PostMapping(value = "space/add")
    public ResponseMessage spaceAdd(@RequestParam("name") String name, @RequestParam("size") int size) throws
            Exception {
        Doc doc = spaceService.add(name, size);
        Common.docList.add(doc);
        ListResponse addResponse = new ListResponse();
        addResponse.setDocList(Common.docList);
        addResponse.setList(Common.list);
        addResponse.setDisk(Common.disk);
        return ResponseMessage.newOkInstance(addResponse);
    }

    /**
     * 删除文件
     *
     * @param name
     * @return
     */
    @PostMapping(value = "space/delete")
    public ResponseMessage spaceDelete(@RequestParam("name") String name) throws Exception {
        spaceService.delete(name);
        ListResponse deleteResponse = new ListResponse();
        deleteResponse.setDocList(Common.docList);
        deleteResponse.setList(Common.list);
        deleteResponse.setDisk(Common.disk);
        return ResponseMessage.newOkInstance(deleteResponse);
    }
}

package com.isp.authorizationserver.domain.port.in;

import com.isp.authorizationserver.adapter.dto.in.ClientRq;
import com.isp.authorizationserver.adapter.dto.out.ClientRp;

public interface ClientsService {
    ClientRp createClient(ClientRq clientRq);
}
